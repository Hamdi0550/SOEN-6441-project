package ddg.view;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import ddg.Config;
import ddg.map.entity.Map;
import ddg.model.MapEditorModel;
import ddg.utils.ValidationTool;
import org.junit.*;
/**
 * This class is test for MapEditor
 *
 * @author Qin yi
 * @date Mar 1, 2017
 */
/**
 * test class for check some function used in the map Editor,
 * Mainly test the Validation function.
 * @author Bo
 * @date Mar 2, 2017
 * 
 */
public class MapEditorTest{
    private MapEditorModel mapModel;
    private Map map;
    private ValidationTool validationTool;

    /**
     * before all test, read the map file, then initial the mapModel
     * @throws Exception
     */
    @Before
    public void testBefore() throws Exception {
        try
	      {
	         FileInputStream fileIn = new FileInputStream(Config.MAP_FILE);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         mapModel = (MapEditorModel) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
        
        if(mapModel==null){
        	mapModel = new MapEditorModel();
        	mapModel.add(new Map());
        }
        
        map = mapModel.getMapByIndex(0);
        validationTool = new ValidationTool(map);

    }

    /**
     * test for check if the hasValidPath() work well
     * @throws Exception
     */
    @Test
    public void testHasValidPath() throws Exception {
        for (int i = 0; i < map.getRow() ; i ++)
            for (int j =0; j < map.getColumn(); j++ ){
                if(map.getLocation()[i][j] == 'i'){
                    validationTool.hasValidPath(i , j);
                    assertTrue(validationTool.isHasvalidpath());
                }

            }
    }
    
    /**
     * 
     * test for check if the hasKey() work well, check whether there is a key on the map
     * @throws Exception
     */
    @Test
    public void testHasKey() throws Exception {
        assertTrue(validationTool.hasKey());
    }
    
    /**
     * test for check if the hasEntryDoor() work well, check whether there is one and only one Entry Door on the map
     * @throws Exception
     */
    @Test
    public void testHasEntryDoor() throws Exception {
        assertTrue(validationTool.hasEntryDoor());
    }
    
    /**
     * test for check if the hasExitDoor() work well, check whether there is a Exit Door on the map
     * @throws Exception
     */
    @Test
    public void testHasExitDoor() throws Exception {
        assertTrue(validationTool.hasExitDoor());
    }

}