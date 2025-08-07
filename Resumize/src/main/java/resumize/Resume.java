package resumize;

    public class Resume {
    	private int id;
    	private String resumeTitle;
        private int userId;
        private String name;
        private String profilePicture;
        private String email;
        private String phone;
        private String linkedin;
        private String location;
        private String bio;
        private String skills;
        private String experience;
        private String education;
        private String interests;
        private String languages;
        private String resumeTemplate;
        
        // Default Constructor
        public Resume() {}

        // Parameterized Constructor
        public Resume(int id, String resumeTitle, int userId, String name, String profilePicture,String email, String phone, String linkedin, String location, String bio, String skills, String experience, String education,String interests, String languages, String resumeTemplate) {
        	this.id = id;
        	this.resumeTitle = resumeTitle;
            this.userId = userId;
        	this.name = name;
            this.profilePicture = profilePicture;
            this.email = email;
            this.phone = phone;
            this.linkedin = linkedin;
            this.location = location;
            this.bio = bio;
            this.skills = skills;
            this.experience = experience;
            this.education = education;
            this.interests = interests;
            this.languages = languages;
            this.resumeTemplate = resumeTemplate; 
        }   

        // Getters and setters for all fields
        
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getResumeTitle() {
			return resumeTitle;
		}

		public void setResumeTitle(String resumeTitle) {
			this.resumeTitle = resumeTitle;
		}
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLinkedin() {
            return linkedin;
        }

        public void setLinkedin(String linkedin) {
            this.linkedin = linkedin;
        }

    	public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getSkills() {
            return skills;
        }

        public void setSkills(String skills) {
            this.skills = skills;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }
        public String getInterests() {
			return interests;
		}

		public void setInterests(String interests) {
			this.interests = interests;
		}
		
        public String getLanguages() {
            return languages;
        }

        public void setLanguages(String languages) {
            this.languages = languages;
        }

        public String getResumeTemplate() {
            return resumeTemplate;
        }

        public void setResumeTemplate(String resumeTemplate) {
            this.resumeTemplate = resumeTemplate;
        }

    }
