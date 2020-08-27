package net.cloudranch.domain;

public class Vido {
	private int id;
	private String vidoName;
	private String vidoUrl;
	private String vidoType;
	private int placeId;
	
	public Vido() {
		super();
	}

	public Vido(int id, String vidoName, String vidoUrl, String vidoType, int placeId) {
		super();
		this.id = id;
		this.vidoName = vidoName;
		this.vidoUrl = vidoUrl;
		this.vidoType = vidoType;
		this.placeId = placeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVidoName() {
		return vidoName;
	}

	public void setVidoName(String vidoName) {
		this.vidoName = vidoName;
	}

	public String getVidoUrl() {
		return vidoUrl;
	}

	public void setVidoUrl(String vidoUrl) {
		this.vidoUrl = vidoUrl;
	}

	public String getVidoType() {
		return vidoType;
	}

	public void setVidoType(String vidoType) {
		this.vidoType = vidoType;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	@Override
	public String toString() {
		return "Vido [id=" + id + ", vidoName=" + vidoName + ", vidoUrl=" + vidoUrl + ", vidoType=" + vidoType
				+ ", placeId=" + placeId + "]";
	}
}
