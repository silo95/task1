package mycompany.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.scene.control.*;
import javax.xml.bind.DatatypeConverter;

public class FXMLController implements Initializable {
    private User loggedUser;
    private DBManager db;
    private String username, password;
    private ObservableList<Post> postOl; 
    private ObservableList<Comment> commentOl;
    
    @FXML
    private TextField usernameField, searchPost, searchComment;
    @FXML
    private PasswordField passwordField ;
    @FXML
    private Label errorLabel, welcomeLabel;
    @FXML
    private Button loginButton, logoutButton, addPost, addComment, deletePost, deleteComment,
                   searchWordPost, searchUserPost, searchWordComment, searchUserComment;
    @FXML
    private final TableView<Post> postTable = new TableView<>();
    @FXML
    private final TableView<Comment> commentTable = new TableView<>();
    @FXML
    private TextArea insertPostAndComment;
    @FXML
    private TableColumn<Comment, String> commentCol;
    @FXML
    private TableColumn<Post, String> postCol;
    
    
    
    @FXML
    private void loginButtonSetOnAction(ActionEvent event) {
        if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            username = String.valueOf(usernameField.getText());
            password = hash(String.valueOf(passwordField.getText()));
         
            loggedUser = new User(username, password);
                
            if(db.isRegistered(username) && db.login(loggedUser)){

                loginButton.setDisable(true);
                logoutButton.setDisable(false);
                usernameField.setEditable(false);
                usernameField.setStyle("-fx-text-inner-color: #bfbfbf;");
                passwordField.setEditable(false);
                passwordField.setStyle("-fx-text-inner-color: #bfbfbf;");
                errorLabel.setText("");
                welcomeLabel.setText("Hello " + username + ", you've written " + 
                        db.getNumberOfPosts(username) +" posts and " +
                        db.getNumberOfComments(username)+ " comments");

                postOl = db.getPosts();
                postTable.setItems(postOl);
                addPost.setDisable(false);
                addComment.setDisable(false);
                deleteComment.setDisable(false);
                deletePost.setDisable(false);
                searchUserComment.setDisable(false);
                searchUserPost.setDisable(false);
                searchWordComment.setDisable(false);
                searchWordPost.setDisable(false);

            }
            else if(!db.isRegistered(username) && db.register(loggedUser)){
                loginButton.setDisable(true);
                logoutButton.setDisable(false);
                usernameField.setEditable(false);
                usernameField.setStyle("-fx-text-inner-color: #bfbfbf;");
                passwordField.setEditable(false);
                passwordField.setStyle("-fx-text-inner-color: #bfbfbf;");
                errorLabel.setText("");
                welcomeLabel.setText("Hello " + username + ", your registration has been successfully completed!");
                postOl = db.getPosts();
                postTable.setItems(postOl);
                addPost.setDisable(false);
                addComment.setDisable(false);
                deleteComment.setDisable(false);
                deletePost.setDisable(false);
                searchUserComment.setDisable(false);
                searchUserPost.setDisable(false);
                searchWordComment.setDisable(false);
                searchWordPost.setDisable(false);
            }
            
            else{
                errorLabel.setText("Username or Password not correct. Please, try again. ");
            }
            
        }
        else{
             errorLabel.setText("Insert all the required fields.");
        }
    }
    
    
    @FXML
    public void logoutButtonSetOnAction(){
        loginButton.setDisable(false);
        logoutButton.setDisable(true);
        usernameField.clear();
        passwordField.clear();
        usernameField.setEditable(true);
        passwordField.setEditable(true);
        welcomeLabel.setText("Insert username and password for login or registration");
        errorLabel.setText("");
        usernameField.setStyle("-fx-text-inner-color: black;");
        passwordField.setStyle("-fx-text-inner-color: black;");
        usernameField.requestFocus();
        addPost.setDisable(true);
        addComment.setDisable(true);
        deleteComment.setDisable(true);
        deletePost.setDisable(true);   
        searchUserComment.setDisable(true);
        searchUserPost.setDisable(true);
        searchWordComment.setDisable(true);
        searchWordPost.setDisable(true);
        postOl.clear();
        commentOl.clear(); 
    }
    
    @FXML
    public void addPostSetOnAction(ActionEvent event){
        String content = insertPostAndComment.getText();
        if(content.length() > 50){
            errorLabel.setText("Text too long. Inserted "+content.length() +" characters. (The maximum is 50)");
        } else if(content.length() == 0){
                errorLabel.setText("The content of post is empty");
        } else{
            errorLabel.setText("");
            db.addPost(content, username);
            postOl = db.getPosts();
            postTable.setItems(postOl);
            insertPostAndComment.clear();
            welcomeLabel.setText("Hello " + username + ", you've written " + 
                        db.getNumberOfPosts(username) +" posts and " +
                        db.getNumberOfComments(username)+ " comments");
        }
}
    
    @FXML
    public void addCommentSetOnAction(ActionEvent event){
        if(postTable.getSelectionModel().getSelectedItem() != null){
            errorLabel.setText("");
            Post p = postTable.getFocusModel().getFocusedItem();
            int index = postTable.getFocusModel().getFocusedIndex();
            String content = insertPostAndComment.getText();

            if(content.length() > 50){
                errorLabel.setText("Text too long. Inserted "+content.length() +" characters. (The maximum is 50)");
            } else if(content.length() == 0){
                errorLabel.setText("The content of comment is empty");
            } else{
                errorLabel.setText("");
                db.addComment(content, username, p.getIdPost());
                commentOl = db.getComments(p.getIdPost());
                commentTable.setItems(commentOl);
                postOl = db.getPosts();
                postTable.setItems(postOl);
                insertPostAndComment.clear();

                postTable.requestFocus();
                postTable.getSelectionModel().select(index);
                postTable.getFocusModel().focus(index);

                welcomeLabel.setText("Hello " + username + ", you've written " + 
                            db.getNumberOfPosts(username) +" posts and " +
                            db.getNumberOfComments(username)+ " comments");
            }
        }
        else{
            errorLabel.setText("Select the post you want to comment");
        }
    }
            
    @FXML
    public void deletePostSetOnAction(ActionEvent event){
        if(postTable.getSelectionModel().getSelectedItem() != null){
            errorLabel.setText("");
            Post p = postTable.getFocusModel().getFocusedItem();

            if(!username.equals(p.getUser())){
                errorLabel.setText("You can delete only your post!");
                postTable.refresh();             
            } else {
                db.deletePost(p);
                postTable.getItems().remove(p);    
                welcomeLabel.setText("Hello " + username + ", you've written " + 
                            db.getNumberOfPosts(username) +" posts and " +
                            db.getNumberOfComments(username)+ " comments");
            }
        }
        else{
            errorLabel.setText("Select a post");
        }
        postTable.setEditable(false);   
    }
    
    @FXML
    public void deleteCommentSetOnAction(ActionEvent event){
        if(commentTable.getSelectionModel().getSelectedItem() != null){
            errorLabel.setText("");
            Comment c = commentTable.getFocusModel().getFocusedItem();

            if(!username.equals(c.getUser())){
                errorLabel.setText("You can delete only your comment!");
                commentTable.refresh();  
            } else{  
                db.deleteComment(c);
                commentTable.getItems().remove(c);
                commentTable.refresh();  
                TableView.TableViewFocusModel<Post> fm =  postTable.getFocusModel();

                Post p = postTable.getSelectionModel().getSelectedItem();
                int index = postTable.getSelectionModel().getSelectedIndex();

                p.setComments(p.getComments() -1);
                postOl.set(index, p);
                postTable.setItems(postOl);
                postTable.refresh();

                postTable.requestFocus();
                postTable.getSelectionModel().select(index);
                postTable.getFocusModel().focus(index);

                welcomeLabel.setText("Hello " + username + ", you've written " + 
                            db.getNumberOfPosts(username) +" posts and " +
                            db.getNumberOfComments(username)+ " comments");

                }    
        }
        else{
            errorLabel.setText("Select a comment");
        }
        commentTable.setEditable(false);        
    }
    
    @FXML
    public void postColSetOnEditCommit(TableColumn.CellEditEvent<Post, String> postStringCellEditEvent){
        Post post = postTable.getSelectionModel().getSelectedItem();
           
        if(!username.equals(post.getUser())){
            errorLabel.setText("Warning: only the author can modify the post");
            postTable.refresh();   
        }
        else{
            if(postStringCellEditEvent.getNewValue().length() > 50){
                errorLabel.setText("Post too long. Inserted " + postStringCellEditEvent.getNewValue().length()+" characters. (The maximum is 50)");
                postTable.refresh(); 
            }
            else{
                errorLabel.setText("");
                db.updatePost(post.getIdPost(), postStringCellEditEvent.getNewValue());
                post.setStrPost(postStringCellEditEvent.getNewValue());
            }
        }
    }
    
    @FXML
    public void commentColSetOnEditCommit(TableColumn.CellEditEvent<Comment, String> commentStringCellEditEvent){
        Comment c = commentTable.getSelectionModel().getSelectedItem();
        if(commentStringCellEditEvent.getNewValue().length() > 50){
            errorLabel.setText("Comment too long. Inserted " + commentStringCellEditEvent.getNewValue().length() +" characters. (The maximum is 50)");
            commentTable.refresh(); 
        }
        else{
            errorLabel.setText("");
            db.updateComment(c.getIdComment(), commentStringCellEditEvent.getNewValue());
        }
    }
    
    @FXML //potrebbe non funzionare perch� non gli ho assegnato un evento in fxml
    public void commentTableSetRowFactory(){
        commentTable.setRowFactory( tv ->{
            TableRow<Comment> row = new TableRow<>();
            row.setOnMouseClicked((Event e) ->{
                Comment c = row.getItem();
                errorLabel.setText("");
                if(!username.equals(c.getUser())){         
                    commentTable.setEditable(false);
                } else {
                    commentTable.setEditable(true);
                }         
            });
                      
            return row;
        });              
    }
    
    @FXML //potrebbe non funzionare perch� non gli ho assegnato un evento in fxml
    public void postTableSetRowFactory(){ 
        postTable.setRowFactory( tv ->{
            TableRow<Post> row = new TableRow<>();
            row.setOnMouseClicked((Event e) ->{
                Post p = row.getItem();
                commentOl = db.getComments(p.getIdPost());
                commentTable.setItems(commentOl);
                errorLabel.setText("");
                if(!username.equals(p.getUser())){
                    postTable.setEditable(false);
                } else {
                    postTable.setEditable(true);
                }         
            });
                      
            return row;
        });
    }    
    
    private String hash(String psw){
        byte[] hash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(psw.getBytes("UTF-8")); 
            return DatatypeConverter.printHexBinary(hash);
        }catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private DBManager getCredential(){
        String srvr, usr, psw;
        srvr = usr = psw = null;
         try (BufferedReader br = new BufferedReader(new InputStreamReader(
                           this.getClass().getResourceAsStream("/config.txt")))) {

            // read line by line
            String line = null;
            int i = 0;
            while (i<3 && (line = br.readLine()) != null) {
                switch (i) {
                    case 0:
                        srvr = line;
                        break;
                    case 1:
                        usr = line; 
                        break;
                    default:
                        psw = line;
                        break;
                }
                i++;
            }
           return new DBManager(srvr, usr, psw);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }  
         return null;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = getCredential();
        commentTableSetRowFactory();
        postTableSetRowFactory();
    }    
}