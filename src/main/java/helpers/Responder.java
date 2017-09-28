package helpers;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 25.06.2015.
 */
public class Responder {

    private List<String> _commands;

    public Responder() {
        _commands = new ArrayList<>();
    }
    public void remove(String id)
    {
        this._commands.add("$('"+id+"').remove();");
    }
    public void update(String id, String data)
    {

        data = data.replaceAll("/'/","\'");
        data = data.replaceAll("/[\n\r]/","");
        data = data.replaceAll("/\\s*</"," <");

        data = data.replaceAll("/>\\s*/","> ");
        this._commands.add("$('"+id+"').update('"+data+"');");
    }

    public void script(String js)
    {
        this._commands.add(js);
    }
    public void redirect(String url)
    {
        this.script("window.location = '"+url+"';");
    }

    public int getCommandCount()
    {
        return this._commands.size();
    }

    public String getOutput()
    {
        return String.join("\\n",this._commands);
    }

    @Override
    public String toString() {
        return ""+_commands;
    }
}
