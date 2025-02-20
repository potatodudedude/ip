package dodo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import dodo.command.AddCommand;
import dodo.command.ByeCommand;
import dodo.command.Command;
import dodo.command.DeleteCommand;
import dodo.command.DueCommand;
import dodo.command.HelpCommand;
import dodo.command.InvalidCommand;
import dodo.command.ListCommand;
import dodo.command.MarkCommand;

public class ParseTest {

    @Test
    public void parseTest1() {
        Command c = Parse.parse("bb");
        assertInstanceOf(ByeCommand.class, c);
    }

    @Test
    public void parseTest2() {
        Command c = Parse.parse("list");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void parseTest3() {
        Command c = Parse.parse("help");
        assertInstanceOf(HelpCommand.class, c);
    }

    @Test
    public void parseTest4() {
        Command c = Parse.parse("");
        Command d = Parse.parse("asdada");
        assertInstanceOf(InvalidCommand.class, c);
        InvalidCommand temp = (InvalidCommand) c;
        assertEquals(temp.getType(), 0);
        assertInstanceOf(InvalidCommand.class, d);
        temp = (InvalidCommand) d;
        assertEquals(temp.getType(), 1);
    }

    @Test
    public void parseTest5() {
        Command c = Parse.parse("todo a");
        assertInstanceOf(AddCommand.class, c);
        AddCommand temp = (AddCommand) c;
        assertEquals(temp.getTaskType(), 0);
        assertEquals(temp.getTaskDescriptions()[1], "a");
    }

    @Test
    public void parseTest6() {
        Command c = Parse.parse("deadline abaa11");
        assertInstanceOf(AddCommand.class, c);
        AddCommand temp = (AddCommand) c;
        assertEquals(temp.getTaskType(), 1);
        assertEquals(temp.getTaskDescriptions()[1], "abaa11");
    }

    @Test
    public void parseTest7() {
        Command c = Parse.parse("event chungagadg asas");
        assertInstanceOf(AddCommand.class, c);
        AddCommand temp = (AddCommand) c;
        assertEquals(temp.getTaskType(), 2);
        assertEquals(temp.getTaskDescriptions()[1], "chungagadg asas");
    }

    @Test
    public void parseTest8() {
        Command c = Parse.parse("mark asasd");
        assertInstanceOf(MarkCommand.class, c);
        MarkCommand temp = (MarkCommand) c;
        assertEquals(temp.getType(), 0);
    }

    @Test
    public void parseTest9() {
        Command c = Parse.parse("unmark asasd");
        assertInstanceOf(MarkCommand.class, c);
        MarkCommand temp = (MarkCommand) c;
        assertEquals(temp.getType(), 1);
    }

    @Test
    public void parseTest10() {
        Command c = Parse.parse("delete 5000");
        assertInstanceOf(DeleteCommand.class, c);
    }

    @Test
    public void parseTest11() {
        Command c = Parse.parse("due in 5000000 years");
        assertInstanceOf(DueCommand.class, c);
    }

}
