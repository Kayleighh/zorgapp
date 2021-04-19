package zorgapp;

import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
 * 
 * Deze klasse wordt gebruikt om het programma pas af te sluiten na 1 seconde. Op deze manier wordt de goodbye message nog goed getoond en is deze nog goed te lezen
 * voordat het programma afgesloten wordt. De seconde wordt geteld door gebruik te maken van de huidige tijd van de computer in miliseconden. Het programma wordt afgesloten door
 * System.exit. De '0' staat voor de status van de exit. 0 betekend een goede exit.
 */
public class TimedExit
{
	Timer		timer	= new Timer();
	TimerTask	exitApp	= new TimerTask()
						{
							public void run()
							{
								System.exit(0);
							}
						};


	public void timedExit()
	{
		timer.schedule(exitApp, new Date(System.currentTimeMillis() + 1 * 1000));
	}
}
