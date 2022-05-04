package by.nyurush.music.controller.command;

public class CommandResult {
    private final String page;
    private final boolean redirect;


    private CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
