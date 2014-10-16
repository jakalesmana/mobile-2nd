package com.dyned.generalenglish.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.SerializedName;

public class GsonExclusion implements ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		SerializedName ns = field.getAnnotation(SerializedName.class);
		return ns == null;
	}

}