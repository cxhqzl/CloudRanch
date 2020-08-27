package net.cloudranch.utils;


public enum PageEnums {
	//error
	error("error/404"),
	
	//user
	
	
	//farmer
	
	
	//admin
	
	;
	
	private String name;
	
	private PageEnums(String iconName) {
		this.setName(iconName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	/**
	 * 判断传入类型是否在枚举中
	 * @param type
	 * @return
	 */
	public static boolean contains(String type){  
        for(PageEnums iconEnum : PageEnums.values()){  
            if(iconEnum.name().equals(type)){  
                return true;  
            }  
        }  
        return false;  
    }
}
