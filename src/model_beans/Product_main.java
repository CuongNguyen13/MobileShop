package model_beans;

import java.util.ArrayList;

public class Product_main {
	private String ID;
	private String name;
	private String type;
	private Branch branch;
	private String status;
	private String update_date;
	private String posts;
	private int quantity;
	private int sale_quantity;
	private int view;
	private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	private ArrayList<Color_main> colors = new ArrayList<Color_main>();

	public Product_main(String iD, String name, String type, Branch branch, String status, String update_date,
			String posts, int quantity, int sale_quantity, int view, ArrayList<Attribute> attributes,
			ArrayList<Color_main> colors) {
		super();
		this.ID = iD;
		this.name = name;
		this.type = type;
		this.branch = branch;
		this.status = status;
		this.update_date = update_date;
		this.posts = posts;
		this.quantity = quantity;
		this.sale_quantity = sale_quantity;
		this.view = view;
		this.attributes = attributes;
		this.colors = colors;
	}

	public Product_main() {
		super();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getPosts() {
		return posts;
	}

	public void setPosts(String posts) {
		this.posts = posts;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSale_quantity() {
		return sale_quantity;
	}

	public void setSale_quantity(int sale_quantity) {
		this.sale_quantity = sale_quantity;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<Color_main> getColors() {
		return colors;
	}

	public void setColors(ArrayList<Color_main> colors) {
		this.colors = colors;
	}

}
