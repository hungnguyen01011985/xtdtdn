package vn.toancauxanh.service;

import org.zkoss.util.resource.Labels;

public class testSplit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "sqFlow_LuuGiaiDoanMot_btngoTask_btn";
		/*int start = text.indexOf("@");
		int close = text.indexOf("@", start + 1);
		while (start != -1 && close != -1) {
			text.substring(start + 1, close);
			start = text.indexOf("@", close + 1);
			close = text.indexOf("@", start + 1);
		}*/
		System.out.println("z"+Labels.getLabel("bpmn.button.mota"));
		int startButton = text.indexOf("_btn");
		int closeButton = text.indexOf("_btn", startButton + 1);
		int lengthButton = "_btn".length();
		
		System.out.println("chuoi: "+ text.substring(startButton + lengthButton, closeButton));
		System.out.println("zz : " +text.substring(0, text.indexOf("_btn")));
	}

}
