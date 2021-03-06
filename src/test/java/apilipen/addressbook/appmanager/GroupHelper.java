package apilipen.addressbook.appmanager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import apilipen.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import apilipen.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

	GroupHelper(WebDriver wd) {
	//  this.driver = wd;
	  super(wd);
	}

	public void returnToGroupPage() {
		clickElement(By.linkText("group page"));
	}

	public void submitGroupCreation() {
		clickElement(By.name("submit"));
	}

	

	public void fillGroupForm(GroupData groupData) {
	      setText(By.name("group_name"), groupData.getName());
	      setText(By.name("group_header"), groupData.getHeader());
	      setText(By.name("group_footer"), groupData.getFooter());

	}

	
	public void initGropCreation() {
		clickElement(By.name("new"));
	}

	public void deleteSelectedGroup() {
		clickElement(By.name("delete"));
	}

	public void selectGroup(int index) {	
	driver.findElements(By.name("selected[]")).get(index).click();
	}

	private void selectGroupById(int id) {
		driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
		
	}
	
	
	public void initGroupModification() {
		clickElement(By.name("edit"));
		
	}

	public void submitGroupModification() {
		clickElement(By.name("update"));
		
		
	}

	public void create(GroupData groupData) {
		 initGropCreation();
		 fillGroupForm(groupData);
		 submitGroupCreation();
		groupCache = null;
		 returnToGroupPage();
		
	}
	
	public void modify(int index, GroupData newGroup) {
		selectGroup(index);  
		initGroupModification();  
		fillGroupForm (newGroup);
		submitGroupModification();
		returnToGroupPage();
	}
	
	
	public void modify( GroupData newGroup) {
		selectGroupById(newGroup.getId()); 
		initGroupModification();  
		fillGroupForm (newGroup);
		submitGroupModification();
		groupCache = null;

		returnToGroupPage();
	}
	
	public void delete(int index) {
		selectGroup(index);  
    	 deleteSelectedGroup();
    	 returnToGroupPage();
	} 

	public void delete(GroupData group) {
		selectGroupById(group.getId());  
   	 deleteSelectedGroup();
		groupCache = null;
   	 returnToGroupPage();
		
	}
	


	public boolean isThereAGroup() {
		
		return isElementPresent(By.name("selected[]"));
	}

	public int сount() {

		return driver.findElements(By.name("selected[]")).size();
	}


	public List<GroupData> list() {
		List <GroupData> groups = new ArrayList<GroupData>() ;		
		List <WebElement> elements  = driver.findElements(By.cssSelector("span.group"));
		for ( WebElement element : elements){
			String name = element.getText();
			
			int id = Integer.parseInt(element.findElement(By.tagName("input"))
					               .getAttribute("value"));
		//	GroupData group = new GroupData ().withId(id).withName(name);
			// new GroupData (id, name , null, null );
			groups.add(new GroupData ().withId(id).withName(name));
			System.out.println(name +" " +  id);
		}
		
		return groups;
	}

	
	public Set <GroupData> allGroupslist() {
		Set <GroupData> groups = new HashSet<GroupData>() ;		
		List <WebElement> elements  = driver.findElements(By.cssSelector("span.group"));
		for ( WebElement element : elements){
			String name = element.getText();
			
			int id = Integer.parseInt(element.findElement(By.tagName("input"))
					               .getAttribute("value"));
			groups.add(new GroupData ().withId(id).withName(name));
			System.out.println(name +" " +  id);
		}
		
		return groups;
	}


	private Groups groupCache = null;

	public Groups all() {

		 if (groupCache != null){
			 return new Groups(groupCache);
		 }

		groupCache = new Groups() ;
		List <WebElement> elements  = driver.findElements(By.cssSelector("span.group"));
		for ( WebElement element : elements){
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			groupCache.add(new GroupData ().withId(id).withName(name));
			System.out.println(name +" " +  id);
		}

		return new Groups(groupCache);
	}
	
	
}
