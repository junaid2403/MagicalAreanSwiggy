package main.java.com.swiggy.magicalarena.code;
import main.java.com.swiggy.magicalarena.code.interfaces.*;
import main.java.com.swiggy.magicalarena.code.classes.*;


public class Main {
    public static void main(String[] args) {
        final TeamMember teamMemberA = new Player(100, 10, 5);
        final TeamMember teamMemberB = new Player(50, 5, 10);

        final GameObserver audience;
        if(teamMemberA.getHealth() < teamMemberB.getHealth())
            audience = new Audience("PlayerA", "PlayerB");
        else
            audience = new Audience("PlayerB", "PlayerA");

        final BattleMain battleMain = new BattleMain(teamMemberA, teamMemberB, audience);

        final BattleContext context = new BattleContext();

        context.handle(battleMain);
    }
}