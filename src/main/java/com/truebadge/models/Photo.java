package com.truebadge.models;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class Photo {
	    @Id
	    private ObjectId _id;
	    	
	    private String title;
	    private Binary image;
	    
	    public Photo() {}
	    
	    public Photo(ObjectId id, String title, Binary image) {
			super();
			this._id = id;
			this.title = title;
			this.image = image;
		}


		// ObjectId needs to be converted to string
	    public String get_id() { return _id.toHexString(); }
	    public void set_id(ObjectId _id) { this._id = _id; }

		public String getTitle() {return title;	}
		public void setTitle(String title) {
			this.title = title;
		}

		public Binary getImage() {
			return image;
		}
		public void setImage(Binary image) {
			this.image = image;
		}
	  
}
