package uk.ac.lancs.e_science.profile2.tool.pages.windows;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;

import uk.ac.lancs.e_science.profile2.api.Profile;
import uk.ac.lancs.e_science.profile2.api.SakaiProxy;
import uk.ac.lancs.e_science.profile2.tool.ProfileApplication;
import uk.ac.lancs.e_science.profile2.tool.components.FocusOnLoadBehaviour;
import uk.ac.lancs.e_science.profile2.tool.pages.BasePage;

public class AddFriend extends Panel {

	private static final long serialVersionUID = 1L;
	private transient Logger log = Logger.getLogger(AddFriend.class);
	private transient SakaiProxy sakaiProxy;
	private transient Profile profile;

	
	public AddFriend(String id, final ModalWindow window, final BasePage basePage, final String userX, final String userY){
        super(id);

        //get API's
        sakaiProxy = ProfileApplication.get().getSakaiProxy();
        profile = ProfileApplication.get().getProfile();
        
        //get friendName
        final String friendName = sakaiProxy.getUserDisplayName(userY);
                
        //window setup
		window.setTitle(new StringResourceModel("title.friend.add", null, new Object[]{ friendName } )); 
		window.setInitialHeight(100);
		window.setInitialWidth(400);
		
        //text
		final Label text = new Label("text", new StringResourceModel("text.friend.add", null, new Object[]{ friendName } ));
        text.setEscapeModelStrings(false);
        text.setOutputMarkupId(true);
        add(text);
                   
        //setup form		
		Form form = new Form("form");
		form.setOutputMarkupId(true);
		
		//submit button
		AjaxFallbackButton submitButton = new AjaxFallbackButton("submit", new ResourceModel("button.friend.add"), form) {
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				
				/* double checking */
				
				//friend?
				//boolean friend = profile.isUserXFriendOfUserY(userX, userY);
				boolean friend=true;
				if(friend) {
					text.setModel(new StringResourceModel("error.friend.already", null, new Object[]{ friendName } ));
					this.setEnabled(false);
					this.add(new AttributeModifier("class", true, new Model("disabled")));
					target.addComponent(text);
					target.addComponent(this);
					return;
				}
				
				/*
				boolean friend = false;
				boolean friendRequestToThisPerson = false;
				boolean friendRequestFromThisPerson = false;

				//friend?
				friend = profile.isUserXFriendOfUserY(userX, userY);

				//if not friend, has a friend request already been made to this person?
				if(!friend) {
					friendRequestToThisPerson = profile.isFriendRequestPending(userX, userY);
				}
				
				//if not friend and no friend request to this person, has a friend request been made from this person to the current user?
				if(!friend && !friendRequestToThisPerson) {
					friendRequestFromThisPerson = profile.isFriendRequestPending(userY, userX);
				}
		        
				//set text appropriately - disable submit if required
				if(friend) {
			        text.setModel(new StringResourceModel("text.friend.already", null, new Object[]{ friendName } ));
			        submitButton.setVisible(false);
				} else if (friendRequestToThisPerson || friendRequestFromThisPerson) {
			        text.setModel(new StringResourceModel("text.friend.already.pending", null, new Object[]{ friendName } ));
			        submitButton.setVisible(false);
				} else {
			        //ok
				}
				*/
				
				
				
				
				
				
				
				
				
				//if ok, request friend
				/*
				if(profile.requestFriend(userX, userY)) {
					basePage.setFriendRequestedResult(true);
				} else {
					//it failed, the logs will say why but we need to UI stuff here.
					basePage.setFriendRequestedResult(false);
					target.appendJavascript("alert('Failed to add friend. Check the system logs.');");
				}
				*/
				//window.close(target);
            }
		};
		submitButton.add(new FocusOnLoadBehaviour());
		form.add(submitButton);
		
        
		//cancel button
		AjaxFallbackButton cancelButton = new AjaxFallbackButton("cancel", new ResourceModel("button.cancel"), form) {
            protected void onSubmit(AjaxRequestTarget target, Form form) {
            	basePage.setFriendRequestedResult(false);
            	window.close(target);
            }
        };
        cancelButton.setDefaultFormProcessing(false);
        form.add(cancelButton);
        
        //add form
        add(form);
        
        
       
        

    }

	
	
	
	
}



