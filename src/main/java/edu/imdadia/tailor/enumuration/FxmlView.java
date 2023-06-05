package edu.imdadia.tailor.enumuration;

import java.util.ResourceBundle;

public enum FxmlView {


  MENU {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("menu.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/menu.fxml";
    }
  },
  HELP {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("help.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/help.fxml";
    }
  },
  HOME {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("home.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/home.fxml";
    }
  },
  LOGIN {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("login.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/login.fxml";
    }
  },

  MEAUSREMENT {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("meausrement.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/meausrement.fxml";
    }
  },
  USER {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("user.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/user.fxml";
    }
  },

  ORDER {
    @Override
    public String getTitle(){
      return getStringFromResourceBundle("order.title");
    }
    @Override
    public String getFxmlFile(){
      return "/fxml/order.fxml";
    }

  },

  CHANGE_PASSWORD {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("changePassword.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/changePassword.fxml";
    }
  },

  USER_INFORMATION {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("userInfo.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/userInfo.fxml";
    }
  },


  MEAUSREMENT_TABLE {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("meausrementTable.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/meausrementTable.fxml";
    }
  },



  Customer {
    @Override
    public String getTitle() {
      return getStringFromResourceBundle("customer.title");
    }

    @Override
    public String getFxmlFile() {
      return "/fxml/customer.fxml";
    }
  };




  public abstract String getTitle();

  public abstract String getFxmlFile();

  static String getStringFromResourceBundle(final String key) {
    return ResourceBundle.getBundle("Bundle").getString(key);
  }
}
