package com.douzone.mysitetest.action.guestbook;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.action.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "insertform" : return new InsertFormAction();
		case "insert" : return new InsertAction();
		case "deleteform" : return new DeleteFormAction();
		case "delete" : return new DeleteAction();
		}
		return null;
	}

}
