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
            return String.format("Haoyan's Search Engine");
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        }else if (url.getPath().equals("/search")){
            String[] parameters = url.getQuery().split("=");
                //if (parameters[0].equals("count")) {
                //    num += Integer.parseInt(parameters[1]);
                //    return String.format("Number increased by %s! It's now %s", parameters[1], num);
                //}
                //anser strings
                if (parameters[0].equals("s")) {
                    search = parameters[1];
                    retString = "";
                    for (String answer : answer_strings){
                        if(answer.contains(search)){
                            retString+= (answer + "\n");
                        }
                    }
                    return String.format("Search results: %s", retString);
                    }
        }  else {
            if (url.getPath().contains("/add")) {
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

