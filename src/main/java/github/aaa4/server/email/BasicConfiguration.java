package github.aaa4.server.email;

import java.util.Properties;

public interface BasicConfiguration {

    Properties getConfiguration();
    String getEmailBody();
}
