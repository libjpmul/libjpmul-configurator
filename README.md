libjpmul-Configurator
=====================

# About
libjpmul-Configurator is a minimal graphical user interface to alter configuration parameters of [libjpmul](https://github.com/libjpmul/libjpmul/) at runtime. It is easily integrated with any application using libjpmul.

# Getting started
## Step 1: Get libJPMul-Configurator
[Click here to download the source archive](https://github.com/libjpmul/libjpmul-configurator/archive/master.zip)

[Click here to download the packaged library](https://github.com/libjpmul/libjpmul-configurator/blob/master/build/libjpmul-configurator.jar)
## Step 2: Integrate it
Include the source or .jar in your project path. Build the project to see that everything is still working.
libjpmul-Configurator includes two classes, _ConfigurationModel_ and _ConfigPanel_. _ConfigPanel_ extends _JScrollPane_, so to integrate it we simple add it to a _JFrame_, then register _ConfigurationModel_ with it.

### Minimal example
```java
JFrame frame = new JFrame("libJPMul-Configurator");  
ConfigPanel panel = new ConfigPanel();
ConfigurationModel model = new ConfigurationModel();
model.addPropertyChangeListener(panel);
frame.add(panel);
frame.pack();
frame.setVisible(true);
```

# Licence
libjpmul-Configurator is available under the modified 3-clause BSD license. See the LICENSE file for more info.
