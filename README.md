## For developers
If you want to create Limbo using api you can follow this steps:
### Get started
**Maven**:
```xml
<repositories>
   <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
   </repository>
</repositories>
<dependencies>
    <dependency>
       <groupId>com.github.bivashy.NanoLimboPlugin</groupId>
       <artifactId>api</artifactId>
       <version>1.0.11</version>
    </dependency>
</dependencies>
```
**Gradle**:
```
allprojects {
   repositories {
      maven { url 'https://jitpack.io' }
   }
}
dependencies {
    implementation 'com.github.bivashy.NanoLimboPlugin:api:1.0.11'
}
```
### How to use API?
That's easy!:
```java
LimboConfig config = new YamlLimboConfig(Paths.get("./"), classLoader).load();
CommandHandler<Command> commandHandler = new ConsoleCommandHandler();
LimboServer server = new LimboServer(config, commandHandler, getClass().getClassLoader());
server.start();

// When you are done
server.stop();
```
Here we are passing 3 arguments:
1. LimboConfig - Configures limbo server, defines `SocketAddress`, join message, title, dimension type, and etc.
2. CommandHandler - Simple CommandHandler. It used by limbo for registering commands. Useful for console only, redundant for Bukkit, Bungee, and other platforms.
3. ClassLoader - used only for loading dummy dimension files from the "resources" directory. It loads all dimensions from the "dimension" folder, which can be found at [this link](https://github.com/bivashy/NanoLimboPlugin/tree/main/api/src/main/resources/dimension).
---

If you don't want to create or load config file, just implement `LimboConfig` interface, just like that:

```java
import java.net.SocketAddress;

public class CustomLimboConfig implements LimboConfig {
   /**
    * Disables debug entirely
    */
   @Override
   public int getDebugLevel() {
      return -1;
   }

   /**
    * Set F3 brand text to the "Some brand text"
    */
   @Override
   public String getBrandName() {
      return "Some brand text";
   }

   /**
    * Launches limbo on the localhost:25555
    */
   @Override
   public SocketAddress getAddress(){
       return new InetSocketAddress("localhost", 25555);
   }

   // Implement all methods
}
```
Then just pass to the LimboServer:
```java
LimboConfig config = new CustomLimboConfig();
LimboServer server = new LimboServer(config, commandHandler, getClass().getClassLoader()); 
```

---

If you don't want limbo commands:

```java
import java.util.Collections;

public class CustomCommandHandler implements CommandHandler<Command> {
   @Override
   public Collection<Command> getCommands() {
      return Collections.emptyList();
   }

   public void register(T command) {
   }

   public boolean executeCommand(String input) {
   }
}
```

Then just pass to the LimboServer:
```java
CommandHandler<Command> commandHandler = new CustomCommandHandler();
LimboServer server = new LimboServer(config, commandHandler, getClass().getClassLoader()); 
```
## NanoLimbo

This is a lightweight Minecraft limbo server, written in Java with Netty.
The main goal of this project is maximum simplicity with a minimum number of sent and processed packets.
The limbo is empty; there is no ability to set a schematic building since this is not necessary.
You can send useful information via chat or boss bar.

The server is fully clear. It is only able to keep a lot of players while the main server is down.

General features:
* High performance. The server doesn't save or cache any useless (for limbo) data.
* Doesn't spawn threads per player. Use a fixed thread pool.
* Support for **BungeeCord** and **Velocity** info forwarding.
* Support for [BungeeGuard](https://www.spigotmc.org/resources/79601/) handshake format.
* Multiple versions support.
* Fully configurable.
* Lightweight. App size around **3MB**.

![](https://i.imgur.com/sT8p1Gz.png)

### Versions support

Symbol `X` means all minor versions.

- [x] 1.7.X
- [x] 1.8.X
- [x] 1.9.X
- [x] 1.10.X
- [x] 1.11.X
- [x] 1.12.X
- [x] 1.13.X
- [x] 1.14.X
- [x] 1.15.X
- [x] 1.16.X
- [x] 1.17.X
- [x] 1.18.X
- [x] 1.19.X
- [x] 1.20.X
- [x] 1.21.X

The server **doesn't** support snapshot versions.

### Commands

* `help` - Show help message
* `conn` - Display number of connections
* `mem` - Display memory usage stats
* `stop` - Stop the server

Note that the server also will be closed correctly if you just press `Ctrl+C`.

### Installation

Required software: JRE 11+

The installation process is simple.

1. Download the latest version of the program [**here**](https://github.com/Nan1t/NanoLimbo/releases).
2. Put the jar file in the folder you want.
3. Create a start script as you did for Bukkit or BungeeCord, with a command like this:
   `java -jar NanoLimbo-<version>.jar`
4. The server will create `settings.yml` file, which is the server configuration. 
5. Configure it as you want and restart the server.

### Player info forwarding

The server supports player info forwarding from the proxy. There are several types of info forwarding:

* `LEGACY` - The **BungeeCord** IP forwarding.
* `MODERN` - **Velocity** native info forwarding type.
* `BUNGEE_GUARD` - **BungeeGuard** forwarding type.

If you use BungeeCord, or Velocity with `LEGACY` forwarding, just set this type in the config.  
If you use Velocity with `MODERN` info forwarding, set this type and paste the secret key from
Velocity config into `secret` field.
If you installed BungeeGuard on your proxy, then use `BUNGEE_GUARD` forwarding type.
Then add your tokens to `tokens` list.

### Contributing

Feel free to create a pull request if you find some bug or optimization opportunity, or if you want
to add some functionality that is suitable for a limbo server and won't significantly load the server.

### Building

Required software:

* JDK 11+
* Gradle 7+ (optional)

To build a minimized jar, go to the project root directory and run in the terminal:

```
./gradlew shadowJar
```

### Contacts

If you have any questions or suggestions, join our [Discord server](https://discord.gg/4VGP3Gv)!
