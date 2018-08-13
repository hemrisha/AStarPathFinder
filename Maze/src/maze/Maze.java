package maze;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import static maze.Maze.printMaze;

/**
 *
 * @author A221137
 */
public class Maze {
 private static char [][] maze;
 private static int startrow,startcol,endrow,endcol;
 private static ArrayList<String> mazeBuffer;
 
 public static void initializingMaze(String fileName)
 {
     startrow = startcol = endrow = endcol=  -1;
     
     mazeBuffer = new ArrayList<>();
     int numcols = 0;
     try
     {
         Scanner file = new Scanner(new File(fileName));
         while(file.hasNext())
         {
             String nextLine = file.nextLine();
             mazeBuffer.add(nextLine);
             if (nextLine.length()>numcols)
                 numcols = nextLine.length();
         }
     }
     catch(Exception e)
     {
         System.out.println(fileName + "has an issue");
     }
     int numrows = mazeBuffer.size();
     
     maze = new char[numrows][numcols];
        for(int r = 0;r < numrows; r++)
    {
        String row = mazeBuffer.get(r);
        for (int c = 0; c<numcols; c++)
        {
            if(row.length()>= c)
                maze[r][c]=row.charAt(c++);
            else
                maze[r][c]='*';
            
            if(maze[r][c]=='S')
            {   
                startrow = r;
                startcol = c;
            }
            if (maze[r][c]=='E')
            {
                endrow = r;
                endcol = c;
            }
        }
    }
     System.out.println("Map loaded");
 }
 
 public static boolean solveMaze(int r, int c)
{
  if(r < 0 ||c < 0 ||r >= maze.length || c >= maze[0].length)
      return false;
  
  if(maze[r][c]!= '.' && maze [r][c]!= 'S')
    return false;
    
  if(maze[r][c]=='E')
        return true;
    
  maze[r][c]= '"';
    
    if (solveMaze(r-1,c))
    {
        maze[r][c] = '.';
        return true;
    }
    if (solveMaze(r+1,c))
    {
        maze[r][c] = '.';
        return true;
    }
    if (solveMaze(r,c-1))
    {
        maze[r][c] = '.';
        return true;
    }
    if (solveMaze(r,c+1))
    {
        maze[r][c] = '.';
        return true;
    }
    return false; 
    
    }
 public static void printMaze()
 {
     for (char[]row: maze)
     {
         for(char c:row)
             System.out.print(c);
         System.out.println();
     }
     System.out.println();
 }
 public static void main(String[]args)
 {
     initializingMaze("map.txt");
     
     printMaze();
     if (solveMaze(startrow,startcol))
         printMaze(); 
     else 
         printMaze();
     System.out.println("No path found");
 }

}




