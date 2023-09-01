package config;

import org.aeonbits.owner.Config;


@Config.Sources("classpath:config/remote.properties")
public interface WebConfig extends Config {

    @Key("remoteDriverUrl")
    String getRemoteUrl();

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();

    @Key("device")
    @DefaultValue("Google Pixel 3")
    String getDevice();

    @Key("osVersion")
    @DefaultValue("9.0")
    String getOsVersion();

    @Key("app")
    @DefaultValue("bs://sample.app")
    String getApp();

}
