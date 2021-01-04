# Kalev

Kalev is a structured logger for JVM (Java, Kotlin etc)

The idea of `Kalev` is pretty similar to `Timber`. Library provides `Kalevipoeg` interface with which you may implement various behaviour for log entries reaction.

## Packages

`kalev-lib` provides pure JVM implementation. Doesn't contains any implementation of `Kalevipoeg`  
`kalev-android` contains bridge between Kalev and Android's log system. `PrintPoeg` format log entry as JSON string and print it to LogCat.  
`kalev-okhttp` provide interceptor for `okhttp` to log network requests in Kalev-way


## Usage

Two easy steps:

1. Add any `Kalevipoeg` instances you want on application start. In Android application `onCreate` is a most suitable place
2. Call Kalev's static methods everywhere throughout your app.

Check full Android sample at `sample` dir

### Fields

Kalev encourages careful, structured logging through logging fields instead of long, unparseable error messages. For example, instead of: `Log.e("Failed to send event %s to topic %s with key %d", userEvent, selectedTopic, eventKey)`, you should log the much more discoverable:

```kotlin
Kalev.with("event", userEvent)
    .with("key", eventKey)
    .with("topic", selectedTopic)
    .e("Failed to send event")
```

Note, that `with` call is optional

### Level logging

Kalev provides 5 levels of logs: verbose, debug, info, warning and error.

```kotlin
Kalev.v("Fragment created")
Kalev.v(throwable, "Fragment wasn't added")

Kalev.d("User logged in")
Kalev.d(throwable, "User error")

Kalev.i("Added item to bucket")
Kalev.i(throwable, "Busket is full")

Kalev.w("Paying for order")
Kalev.w(throwable, "Failed to pay")

Kalev.e("Smth happens")
Kalev.e(throwable, "Smth happened")
```

### Entries

Besides the fields added with `with` some fields are automatically added to all logging events:

`severity` The logging level. E.g. `i`.  
`timestamp` The timestamp when the entry was created.  
`message` The logging message passed to {v, d, i, w, e} after the `with` call. E.g. `Failed to send event.`

### Network Logging

Add `LoggingInterceptor` to your `OkHttpClient` to log network requests in Kalev-way.
Examples of network logs:

[http://httpbin.org/get?id=42](http://httpbin.org/get?id=42)  
```json
{
  "message": "network",
  "method": "GET",
  "path": "\/get",
  "id": "42",
  "response.code": 200,
  "response.body": {
    "args": {
      "id": "42"
    },
    "headers": {
      "Accept-Encoding": "gzip",
      "Content-Type": "application\/json",
      "Host": "httpbin.org",
      "User-Agent": "okhttp\/3.12.12",
      "X-Amzn-Trace-Id": "Root=1-5ed772b8-71eaec363ea2adcca9201200"
    },
    "url": "https:\/\/httpbin.org\/get?id=42"
  }
}
```

[http://httpbin.org/unknown](http://httpbin.org/unknown)
```json
{
  "message": "network",
  "method": "GET",
  "path": "\/unknown",
  "id": "42",
  "response.code": 404,
  "response.body": "<!DOCTYPE HTML PUBLIC \"-\/\/W3C\/\/DTD HTML 3.2 Final\/\/EN\">\n<title>404 Not Found<\/title>\n<h1>Not Found<\/h1>\n<p>The requested URL was not found on the server.  If you entered the URL manually please check your spelling and try again.<\/p>\n"
}
```

### FastLog
Kalev creates new object for every log entry which may abuse performance in case of stream of entries. You can go around of this issue with FastLog. 

#### FastLog usage
There is `FastLog` interface which describes fast logger.  
`kalev-lib` provides `SystemFastLog` implementation which prints messages to standard output stream   
`kalev-android` provides `AndroidFastLog` implementation which prints messages to Android's LogCat
##### Initialisation:   
`Kalev.fastLog = AndroidFastLog()`  
##### Logging
`Kalev.fastLog?.v("Activity created")`

## Gradle
Add this to your dependencies block.
```
implementation 'eu.bolt:kalev:$latest'
```

To use an android extension use this dependency instead:
```
implementation 'eu.bolt:kalev-android:$latest'
```

Kalev-okhttp package:
```
implementation 'eu.bolt:kalev-okhttp:$latest'
```

## Naming

Kaleva - also known as Kalevi or Kalev - and his sons are important heroic figures in Estonian, Finnish and Karelian mythology. [Wiki](https://en.wikipedia.org/wiki/Kalevi_(mythology))

## License
```
MIT License

Copyright (c) 2020 Bolt Technologies OÜ

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```