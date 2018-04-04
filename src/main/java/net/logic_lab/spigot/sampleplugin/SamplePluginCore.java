package net.logic_lab.spigot.sampleplugin;

import org.bukkit.plugin.java.JavaPlugin;
import java.sql.*;

public class SamplePluginCore extends JavaPlugin {

    Connection con = null;


    @Override
    public void onEnable(){
        getLogger().info("プラグインが有効になったよ！");

        if( con == null ){
            getLogger().info("未接続の為接続します。");

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/minecraft","root","");
                getLogger().info("接続完了");
            }
            catch ( InstantiationException | IllegalAccessException | ClassNotFoundException e ){
                getLogger().info("JDBCドライバのロードに失敗しました。");
            }
            catch ( SQLException e ){
                getLogger().info("接続できませんでした。");
            }
        }

    }

    @Override
    public void onDisable(){
        getLogger().info("プラグインが無効になったよ！");
        if( con != null ){
            try {
                getLogger().info("切断します。");
                con.close();
                con = null;
            }
            catch( SQLException e ){
                getLogger().info("切断に失敗しました。");
            }
        }
    }

}
