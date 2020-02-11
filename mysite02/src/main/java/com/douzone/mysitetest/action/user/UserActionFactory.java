package com.douzone.mysitetest.action.user;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.action.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "joinform" : return new JoinFormAction();
		case "join" : return new JoinAction();
		case "joinsuccess" : return new JoinSuccessFromAction();
		case "loginform" : return new LoginFormAction();
		case "login" : return new LoginAction();
		case "logout" : return new LogoutAction();
		case "updateform" : return new UpdateFormAction();
		
//		default : return new MainAction();
		}
		return null;
	}

}
