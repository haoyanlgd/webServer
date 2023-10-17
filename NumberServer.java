import java.io.IOException;
import java.net.URI;
import java.util.ArrayList; // import the ArrayList class


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> answer_strings = new ArrayList<String>();
    String search = "";
    String retString = "";

    public String handleRequest(URI url) {
    if (url.getPath().equals("/")) {
        retString = "";
        num = 0;
        for (String answer : answer_strings){
            num ++;
            retString+= (String.valueOf(num)+"." +" " +answer + "\n");
            
        }
        return String.format("%s", retString);
        
        }  else {
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                //if (parameters[0].equals("count")) {
                //    num += Integer.parseInt(parameters[1]);
                //    return String.format("Number increased by %s! It's now %d", parameters[1], num);
                //}
                //anser strings
                if (parameters[0].equals("s")) {
                    answer_strings.add(parameters[1]);
                    return String.format("answer_strings added by %s! It's now %s", parameters[1], answer_strings);
                }
            }
           
        } 
        return "404 Not Found!";
    }
}


class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

