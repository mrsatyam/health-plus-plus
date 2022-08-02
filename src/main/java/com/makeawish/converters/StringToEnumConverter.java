package com.makeawish.converters;

import org.springframework.core.convert.converter.Converter;

import com.makeawish.model.Gender;

public class StringToEnumConverter implements Converter<String, Gender> {

	@Override
	public Gender convert(String source) {
		if (source.equals("Male")) {
			return Gender.MALE;
		} else if (source.equals("Female")) {
			return Gender.FEMALE;
		}
		return Gender.OTHER;
	}

}
