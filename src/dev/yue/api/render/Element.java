package dev.yue.api.render;

public class Element extends Component {

    public Object value;

    public Element  (Component parent, Object value) {
        this.parent = parent;
        this.value = value;
    }
    
    public void render (Canvas canvas, int mouseX, int mouseY, int delta) {
        
    }
    
    public void key (int keyCode, int scanCode, int modifiers) {
        
    }
    
    public boolean click (int mouseX, int mouseY, int button) {
        return false;
    }

    public Element drag (int mouseX, int mouseY, int deltaX, int deltaY, int button) {
        return null;
    }
}
