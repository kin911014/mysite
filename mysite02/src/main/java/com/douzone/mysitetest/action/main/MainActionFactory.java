package com.douzone.mysitetest.action.main;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.action.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
