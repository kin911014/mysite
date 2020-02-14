package com.douzone.mysite.action.board;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "listform" : return new ListFormAction(); 
		case "writeform" : return new WriteFormAction();
		case "write" : return new WriteAction();
		case "viewform" : return new ViewFormAction();
		case "modifyform" : return new ModifyFormAction();
		case "modify" : return new ModifyAction();
		case "answerform" : return new AnswerFormAction();
		case "answer" : return new AnswerAction();
		
		}
		
		return null; 
	}

}
