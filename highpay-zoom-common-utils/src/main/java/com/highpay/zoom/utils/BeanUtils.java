package com.highpay.zoom.utils;

import org.apache.commons.beanutils.*;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Admin on 2017/5/24.
 */
public class BeanUtils {
	private static Log log = LogFactory.getLog(BeanUtils.class);
	public static final String ADD = "add";
	public static final String DEL = "del";
	public static final String UPDATE = "update";

	public BeanUtils() {
	}

	public static void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
		ConvertUtils.register(new DateConverter(), Date.class);
		BeanUtilsBean beanUtil = new BeanUtilsBean();
		if(dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		} else if(orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		} else {
			if(log.isDebugEnabled()) {
				log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
			}

			int i;
			String name;
			Object value;
			if(orig instanceof DynaBean) {
				DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();

				for(i = 0; i < origDescriptors.length; ++i) {
					name = origDescriptors[i].getName();
					if(beanUtil.getPropertyUtils().isWriteable(dest, name)) {
						value = ((DynaBean)orig).get(name);
						copyProperty(dest, name, value);
					}
				}
			} else if(orig instanceof Map) {
				Iterator names = ((Map)orig).keySet().iterator();

				while(names.hasNext()) {
					name = (String)names.next();
					if(beanUtil.getPropertyUtils().isWriteable(dest, name)) {
						value = ((Map)orig).get(name);
						copyProperty(dest, name, value);
					}
				}
			} else {
				PropertyDescriptor[] origDescriptors = beanUtil.getPropertyUtils().getPropertyDescriptors(orig);

				for(i = 0; i < origDescriptors.length; ++i) {
					name = origDescriptors[i].getName();
					if(!"class".equals(name) && beanUtil.getPropertyUtils().isReadable(orig, name) && beanUtil.getPropertyUtils().isWriteable(dest, name)) {
						try {
							value = beanUtil.getPropertyUtils().getSimpleProperty(orig, name);
							if(value != null) {
								copyProperty(dest, name, value);
							}
						} catch (NoSuchMethodException var7) {
							log.error(var7);
						}
					}
				}
			}

		}
	}

	public static void copyPropertiesForObject(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
		BeanUtilsBean beanUtil = new BeanUtilsBean();
		if(dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		} else if(orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		} else {
			if(log.isDebugEnabled()) {
				log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
			}

			int i;
			String name;
			Object value;
			if(orig instanceof DynaBean) {
				DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();

				for(i = 0; i < origDescriptors.length; ++i) {
					name = origDescriptors[i].getName();
					if(beanUtil.getPropertyUtils().isWriteable(dest, name)) {
						value = ((DynaBean)orig).get(name);
						copyProperty(dest, name, value);
					}
				}
			} else if(orig instanceof Map) {
				Iterator names = ((Map)orig).keySet().iterator();

				while(names.hasNext()) {
					name = (String)names.next();
					if(beanUtil.getPropertyUtils().isWriteable(dest, name)) {
						value = ((Map)orig).get(name);
						copyProperty(dest, name, value);
					}
				}
			} else {
				PropertyDescriptor[] origDescriptors = beanUtil.getPropertyUtils().getPropertyDescriptors(orig);

				for(i = 0; i < origDescriptors.length; ++i) {
					name = origDescriptors[i].getName();
					if(!"class".equals(name) && beanUtil.getPropertyUtils().isReadable(orig, name) && beanUtil.getPropertyUtils().isWriteable(dest, name)) {
						try {
							value = beanUtil.getPropertyUtils().getSimpleProperty(orig, name);
							copyProperty(dest, name, value);
						} catch (NoSuchMethodException var7) {
							log.error(var7);
						}
					}
				}
			}

		}
	}

	public static void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
		StringBuffer sb;
		if(log.isTraceEnabled()) {
			sb = new StringBuffer("  copyProperty(");
			sb.append(bean);
			sb.append(", ");
			sb.append(name);
			sb.append(", ");
			if(value == null) {
				sb.append("<NULL>");
			} else if(value instanceof String) {
				sb.append((String)value);
			} else if(!(value instanceof String[])) {
				sb.append(value.toString());
			} else {
				String[] values = (String[])((String[])value);
				sb.append('[');

				for(int i = 0; i < values.length; ++i) {
					if(i > 0) {
						sb.append(',');
					}

					sb.append(values[i]);
				}

				sb.append(']');
			}

			sb.append(')');
			log.trace(sb.toString());
		}

		if(bean instanceof DynaBean) {
			DynaProperty propDescriptor = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
			if(propDescriptor != null) {
				Converter converter = ConvertUtils.lookup(propDescriptor.getType());
				if(converter != null) {
					value = converter.convert(propDescriptor.getType(), value);
				}

				try {
					PropertyUtils.setSimpleProperty(bean, name, value);
				} catch (NoSuchMethodException var8) {
					log.error("-->Should not have happened", var8);
				}
			} else if(log.isTraceEnabled()) {
				log.trace("-->No setter on 'to' DynaBean, skipping");
			}
		} else {
			sb = null;

			PropertyDescriptor propDescriptor;
			try {
				propDescriptor = PropertyUtils.getPropertyDescriptor(bean, name);
			} catch (NoSuchMethodException var7) {
				propDescriptor = null;
			}

			if(propDescriptor != null && propDescriptor.getWriteMethod() == null) {
				propDescriptor = null;
			}

			if(propDescriptor != null) {
				try {
					PropertyUtils.setSimpleProperty(bean, name, value);
				} catch (NoSuchMethodException var6) {
					log.error("-->Should not have happened", var6);
				}
			} else if(log.isTraceEnabled()) {
				log.trace("-->No setter on JavaBean, skipping");
			}
		}

	}

	public static <T> T registryBean(ConfigurableListableBeanFactory factory, ApplicationContext appContext, String beanName, Class<T> clazz, Map<String, Object> attribute) {
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry)factory;
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(clazz);
		beanDefinition.setLazyInit(false);
		beanDefinition.setAbstract(false);
		beanDefinition.setAutowireCandidate(true);
		beanDefinition.setScope("prototype");
		if(attribute != null) {
			beanDefinition.setPropertyValues(new MutablePropertyValues(attribute));
		}

		registry.registerBeanDefinition(beanName, beanDefinition);
		return (T) appContext.getBean(beanName);
	}

	public static Map<String, List<? extends Object>> compareListObject(List<? extends Object> saveObject, List<? extends Object> dbObject) {
		Assert.notEmpty(saveObject);
		Map<String, List<? extends Object>> map = new HashMap();
		if(CollectionUtils.isEmpty(dbObject)) {
			map.put("add", saveObject);
		} else {
			List<Object> add = new ArrayList();
			List<Object> update = new ArrayList();
			List<Object> del = new ArrayList();
			Iterator i$ = saveObject.iterator();

			Object o;
			while(i$.hasNext()) {
				o = i$.next();
				if(dbObject.contains(o)) {
					update.add(o);
				} else {
					add.add(o);
				}
			}

			i$ = dbObject.iterator();

			while(i$.hasNext()) {
				o = i$.next();
				if(!update.contains(o)) {
					del.add(o);
				}
			}

			map.put("add", add);
			map.put("del", del);
			map.put("update", update);
		}

		return map;
	}
}
