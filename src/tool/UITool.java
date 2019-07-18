package tool;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;

import javax.swing.JDialog;

public class UITool {
	
	/**
	 * 用户提交字段检测，返回没有填写的字段名
	 * */
	public static String allFieldNonEmptyCheck(Map<String, String> fieldMap) {
		for(String key : fieldMap.keySet()) {
			//检查所有字段，一个字段为空就返回空字段名称
			if(fieldMap.get(key).isEmpty() || fieldMap.get(key) == null) {
				return key;
			}
		}
		return "success";
	}
	
	/**
	 * 用户提交字段检测，填入任一字段判定success
	 * */
	public static String searchFieldCheck(Map<String, String> fieldMap) {
		for(String key : fieldMap.keySet()) {
			//任一字段非空
			if(!fieldMap.get(key).isEmpty()) {
				return "success";
			}
		}
		return "error";
	}
	
	/**
	 * 为对话框设置屏幕中心位置
	 * */
	public static void setCentralLocation(Component owner, JDialog dialog, int width, int height) {
		Point point = owner.getLocation();
		Dimension dimension = owner.getSize();
		int x = point.x + (dimension.width - width) / 2;
		int y = point.y + (dimension.height - height) / 2;
		dialog.setLocation(x, y);
	}

	
}
