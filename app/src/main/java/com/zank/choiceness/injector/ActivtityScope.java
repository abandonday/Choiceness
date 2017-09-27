package com.zank.choiceness.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Zank on 2017/9/27.
 *
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivtityScope {

}
