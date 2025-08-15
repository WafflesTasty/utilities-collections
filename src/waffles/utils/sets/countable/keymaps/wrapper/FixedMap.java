package waffles.utils.sets.countable.keymaps.wrapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;

import waffles.utils.sets.countable.keymaps.KeyMap;

/**
 * A {@code FixedMap} defines a {@code KeyMap} backed by a {@code java.util.EnumMap}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <E>  an enum type
 * @param <V>  a value type
 * @see KeyMap
 * @see Enum
 */
public class FixedMap<E extends Enum<E>, V> implements KeyMap.Java<E, V>
{
	private EnumMap<E, V> data;

	/**
	 * Creates a new {@code FixedMap}.
	 */
	public FixedMap()
	{
		data = new EnumMap<>(Type());
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