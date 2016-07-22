[condition][]Check if the user not logged in=$currentUser : CurrentUser(role==Role.EMPTY)
[condition][]Get user logged in Id=$id : Integer()
[condition][]Check if validation is true=eval($check==true)
[condition][]Get validation result from the Service=$check : Boolean()
[condition][]Get user logged in useraname=$username : String()
[consequence][]Log data=System.out.println("(UPDATED) Enter userlogin update "+$username+" | "+$id+" | "+$check);
[consequence][]Set user logged in username=$currentUser.setName($username);
[consequence][]Set user logged in Id=$currentUser.setId($id);
[consequence][]Set user logged in Status to Customer=$currentUser.setRole(Role.CUSTOMER);
[consequence][]Update user logged in data=update($currentUser);
