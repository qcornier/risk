package util;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MenuJeu {
	
	private MenuBar barre;
	private Menu boutonPrincipal;
	private MenuItem element1, element2, element3, element4;
	private MenuListener menuListen;
	private Color bg;
	
	
	public MenuJeu() {
		menuListen = new MenuListener();
		barre = new MenuBar();
		boutonPrincipal = new Menu("Menu");
		bg = new Color(255);
		element1 = new MenuItem("Red");
		element1.addActionListener(menuListen);
		element2 = new MenuItem("Green");
		element2.addActionListener(menuListen);
		element3 = new MenuItem("Blue");
		element3.addActionListener(menuListen);
		element4 = new MenuItem("Yellow");
		element4.addActionListener(menuListen);
		boutonPrincipal.add(element1);
		boutonPrincipal.add(element2);
		boutonPrincipal.add(element3);
		boutonPrincipal.add(element4);
		barre.add(boutonPrincipal);
	}


	public MenuBar getBarre() {
		return barre;
	}


	public void setBarre(MenuBar barre) {
		this.barre = barre;
	}


	public Menu getBoutonPrincipal() {
		return boutonPrincipal;
	}


	public void setBoutonPrincipal(Menu boutonPrincipal) {
		this.boutonPrincipal = boutonPrincipal;
	}


	public MenuItem getElement1() {
		return element1;
	}


	public void setElement1(MenuItem element1) {
		this.element1 = element1;
	}


	public MenuItem getElement2() {
		return element2;
	}


	public void setElement2(MenuItem element2) {
		this.element2 = element2;
	}


	public MenuItem getElement3() {
		return element3;
	}


	public void setElement3(MenuItem element3) {
		this.element3 = element3;
	}


	public MenuItem getElement4() {
		return element4;
	}


	public void setElement4(MenuItem element4) {
		this.element4 = element4;
	}


	public MenuListener getMenuListen() {
		return menuListen;
	}


	public void setMenuListen(MenuListener menuListen) {
		this.menuListen = menuListen;
	}


	public Color getBg() {
		return bg;
	}


	public void setBg(Color bg) {
		this.bg = bg;
	}
}

class MenuListener implements ActionListener, ItemListener{

	 MenuListener(){

	 }

	 public void actionPerformed(ActionEvent e) {
	   MenuItem source = (MenuItem)(e.getSource());
	   String s = "Action event detected."
	     + "    Event source: " + source.getLabel()
	     + " (an instance of " + getClassName(source) + ")";
	   System.out.println(s);
	   
	   //this part changes the background colour
//	   if(source.getLabel().equals("Red")){
//	     bg = Color(220,50,0);
//	   }
//	   if(source.getLabel().equals("Blue")){
//	     bg = Color(30,100,255);
//	   }
//	   if(source.getLabel().equals("Green")){
//	     bg = Color(40,200,0);
//	   }
//	   if(source.getLabel().equals("Yellow")){
//	     bg = Color(220,220,0);
//	   }
//	   if(source.getLabel().equals("Black")){
//	     bg = Color(0);
//	   }
	 }
	 
	 public void itemStateChanged(ItemEvent e) {
	       MenuItem source = (MenuItem)(e.getSource());
	       String s = "Item event detected."
	                  + "    Event source: " + source.getLabel()
	                  + " (an instance of " + getClassName(source) + ")"
	                  + "    New state: "
	                  + ((e.getStateChange() == ItemEvent.SELECTED) ?
	                    "selected":"unselected");
	       System.out.println(s);
	   }


	//gets the class name of an object
	protected String getClassName(Object o) {
	 String classString = o.getClass().getName();
	 int dotIndex = classString.lastIndexOf(".");
	 return classString.substring(dotIndex+1);
	}

}
