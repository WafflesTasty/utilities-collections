package waffles.utils.sets.keymaps.delegate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;

import waffles.utils.sets.keymaps.DelegateMap;

/**
 * A {@code JEnumMap} defines a {@code KeyMap} which is backed by a Java {@code EnumMap}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <E>  an enum type
 * @param <V>  a value type
 * @see DelegateMap
 */
public class JEnumMap<E extends Enum<E>, V> implements DelegateMap<E, V>
{
	private EnumMap<E, V> data;

	/**
	 * Creates a new {@code JEnumMap}.
	 */
	public JEnumMap()
	{
		data = new EnumMap<>(Type());
	}

	
	@Override
	public int hashCode()
	{
		return data.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof JEnumMap)
		{
			JEnumMap<?,?> other = (JEnumMap<?,?>) obj; 
			return data.equals(other.data);
		}
		
		return false;
	}
	
	@Override
	public EnumMap<E, V> Delegate()
	{
		return data;
	}
	
	Class<E> Type()
	{
		ParameterizedType p;
		Type mapType = getClass().getGenericSuperclass();
		if(mapType instanceof ParameterizedType)
		{
			p = (ParameterizedType) mapType;
			Type enumType = p.getActualTypeArguments()[0];
			if(enumType instanceof ParameterizedType)
			{
				p = (ParameterizedType) enumType;
				enumType = p.getRawType();
			}
			
			return (Class<E>) enumType;
		}
		
		return null;
	}
}