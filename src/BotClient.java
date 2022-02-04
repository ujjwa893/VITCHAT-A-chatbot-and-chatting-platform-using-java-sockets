
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client{
    public static void main(String[] args) throws IOException {
        BotClient bot = new BotClient();
        bot.run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(100*Math.random());
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Hello, there. I'm a bot. I will help you with all possible queries in chat !");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String[] arr = message.split(": ");
                String pattern = null;
                for(int i=0;i<arr.length;i++)    //length is the property of the array  
                System.out.println(arr[i]);  
                if(arr[1].matches("(.*)b/(.*)")){
                switch (arr[1].substring(2)) {
                    case "date" : pattern = "d.MM.YYYY";break;
                    case "day" : pattern = "d";break;
                    case "month" : pattern = "MMMM";break;
                    case "year" : pattern = "YYYY";break;
                    case "time" : pattern ="H:mm:ss";break;
                    case "hour" : pattern = "H";break;
                    case "minutes" : pattern = "m";break;
                    case "seconds" : pattern = "s";break;
                    case "vtop" : sendTextMessage( arr[0] + " opened Vtop");
                    try {   Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome https://vtopcc.vit.ac.in/vtop/initialProcess"});  } 
                    catch (Exception ex) { ex.printStackTrace(); }  break;
                    case "moodle" : sendTextMessage( arr[0] + " opened Moodle");
                    try {   Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome https://lms.vit.ac.in/login/index.php"});   } 
                    catch (Exception ex) { ex.printStackTrace(); }  break;
                }}
                if (pattern != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    sendTextMessage("Information for " + arr[0] + ": " + sdf.format(Calendar.getInstance().getTime()));
                }
            }
        }
    }
}
