import com.neuronrobotics.bowlerstudio.BowlerStudioController
import com.neuronrobotics.bowlerstudio.BowlerStudioMenu
import com.neuronrobotics.bowlerstudio.vitamins.VitaminBomManager
import com.neuronrobotics.sdk.common.DeviceManager

import javafx.scene.control.Tab
import javafx.scene.image.ImageView

// code here
if (args==null)
	args="https://github.com/OperationSmallKat/Marcos.git"
println "Starting ZenCad for "+args 
String name = "BoM-"+BowlerStudioMenu.gitURLtoMessage(args)
VitaminBomManager bom=new VitaminBomManager(args);
class TabManagerDevice{
	String myName;
	boolean connected=false;
	ImageView imageView = new ImageView();
	Tab t = new Tab()
	public TabManagerDevice(String name) {
		myName=name;
		
	}
	
	String getName() {
		return myName
	}
	
	boolean connect() {
		connected=true;
		t.setContent(imageView)
		t.setText(myName)
		t.setOnCloseRequest({event ->
			disconnect()
		});
		BowlerStudioController.addObject(t, null)
		return connected
	}
	void disconnect() {
		if(connected) {
			BowlerStudioController.removeObject(t)
		}
		
	}
}
def tabHolder = DeviceManager.getSpecificDevice(name, {
	TabManagerDevice dev = new TabManagerDevice(name)
	dev.connect()
	return dev
})
BowlerStudioController.addObject(tabHolder.t, null)



return null;