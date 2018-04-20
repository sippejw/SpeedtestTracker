import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser
{
    private String fileLocation;
    private Scanner fileParser = null;
    private Double totalDownSpeed = 0.0;
    private Double totalUpSpeed = 0.0;
    private int zeroTimeUpCounter = 0;
    private int zeroTimeDownCounter = 0;
    private int downSpeedCount = 0;
    private int upSpeedCount = 0;
    public void initializer()
    {
        Scanner fileNames = null;
        try
        {
            fileNames = new Scanner(new File("fileNames.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File 1 not found!");
            System.exit(0);
        }
        while(fileNames.hasNext())
        {
            fileLocation = fileNames.nextLine();
            fileLocation = fileLocation.trim();
            fileLocation = "DailyLogs/" + fileLocation;
            parser(fileLocation);
        }
    }
    public void parser(String fileLocation)
    {
        String downCheck = "Download: ";
        String upCheck = "Upload: ";
        String data;
        String rawData;
        try
        {
            fileParser = new Scanner(new File(fileLocation));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File 2 not found!");
            System.exit(0);
        }
        while(fileParser.hasNext())
        {
            data = fileParser.nextLine();
            if (data.startsWith(downCheck))
            {
                downSpeedCount++;
                rawData = (String) data.subSequence(10, 15);
                rawData.trim();
                Double downSpeed = Double.parseDouble(rawData);
                totalDownSpeed += downSpeed;
                if (downSpeed == 0.0)
                {
                    zeroTimeDownCounter++;
                }
            }
            else if(data.startsWith(upCheck))
            {
                upSpeedCount++;
                rawData = (String) data.subSequence(8, 13);
                rawData.trim();
                Double upSpeed = Double.parseDouble(rawData);
                totalUpSpeed += upSpeed;
                if (upSpeed == 0.0)
                {
                    zeroTimeUpCounter++;
                }
            }
        }
    }
    public String result()
    {
        Double downSpeedAverage = totalDownSpeed / downSpeedCount;
        Double upSpeedAverage = totalUpSpeed / upSpeedCount;
        String finalResult = "Over the course of " + downSpeedCount + " download speed captures and " + 
            upSpeedCount + " upload speed captures an average download speed of " + downSpeedAverage + " Mbit/s was found. The average upload speed was " + 
            upSpeedAverage + ". Throughout this course there were a total of " + zeroTimeDownCounter + " downtimes on the download end and " + 
            zeroTimeUpCounter + " downtimes on the upload end.";
        return finalResult;
    }
    public static void main(String[] args)
    {
        FileParser run = new FileParser();
        run.initializer();
        System.out.println(run.result());
    }
}
