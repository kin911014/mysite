package com.douzone.mysite.action.board;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "listform" : return new ListFormAction(); 
		}
		
		return null;
	}

}
