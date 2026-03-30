# 🦈 RMXshark

> **Debugger for the RMX PC Central 2.0** from [Rautenhaus Digital®](https://www.rautenhaus.de/).  
> RMXshark empowers users to get a clear view of what actually happens inside the RMX PC Central 2.0 system.

---

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Building](#building)
- [Running](#running)
- [Usage](#usage)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [RMXnet Protocol](#rmxnet-protocol)
- [Contributing](#contributing)
- [Authors](#authors)

---

## Features

- **Real-Time Monitoring** — Connect to an RMX server over TCP/IP and monitor messages in a live console
- **Message Filtering** — Filter incoming messages by opcode (0x01, 0x04, 0x06, 0x08, 0x20, 0x24, 0x28)
- **Macro Recording & Playback** — Record sequences of commands and replay them
- **Bus Selection** — Switch between RMX-0 and RMX-1 buses
- **Hex Display** — All messages displayed in readable hexadecimal format
- **Auto-Reconnect** — Heartbeat monitoring with automatic reconnection prompts

---

## Architecture

RMXshark is a Java Swing desktop application that communicates with the RMX PC Central 2.0 via TCP sockets using the RMXnet binary protocol.

```
┌──────────────┐       TCP/IP        ┌──────────────────────┐
│              │◄────────────────────►│                      │
│   RMXshark   │   RMXnet Protocol   │  RMX PC Central 2.0  │
│   (Client)   │                     │      (Server)        │
│              │                     │                      │
└──────────────┘                     └──────────────────────┘
```

**Internal Components:**

| Package       | Responsibility                                         |
|---------------|--------------------------------------------------------|
| `connection/` | TCP socket management, message sending/receiving       |
| `console/`    | Swing GUI: console output, menus, dialogs              |
| `makro/`      | Macro recording and playback engine                    |
| `Utilities/`  | Byte manipulation, protocol constants, configuration   |
| `io/`         | File I/O for configuration persistence                 |

---

## Prerequisites

- **Java 11** or higher (tested with Java 17)
- **Gradle 7+** (wrapper not included; install via [sdkman](https://sdkman.io/) or [gradle.org](https://gradle.org/install/))

---

## Building

```bash
# Compile the project
gradle build

# Compile without running tests
gradle build -x test
```

---

## Running

```bash
# Run the application
gradle run
```

On startup, RMXshark will:

1. Open a connection dialog for the RMX server IP and port (default: `127.0.0.1:950`)
2. Launch the debug console
3. Attempt to connect and begin monitoring

---

## Usage

### Sending Messages

Enter messages in the console input field using the format:

```
[SystemAddress],[BitIndex],[Value]
```

| Parameter       | Range  | Description                    |
|-----------------|--------|--------------------------------|
| `SystemAddress` | 1–111  | RMX system address             |
| `BitIndex`      | 0–7    | Bit position within the byte   |
| `Value`         | 0–1    | Bit value to set               |

**Example:** `42,3,1` sets bit 3 of system address 42 to 1.

### Macro Recording

1. Click the **O** button in the toolbar to start recording (turns red)
2. Send commands as usual
3. Click the **O** button again to stop recording
4. Recorded macros appear in the dropdown and are saved to the `Makros/` folder
5. Select a macro from the dropdown to replay it

### Message Filtering

Click the **Filter** button to open the filter dialog. Check/uncheck opcodes to hide/show specific message types.

---

## Testing

```bash
# Run all tests
gradle test

# Run tests with verbose output
gradle test --info
```

Tests cover the core utility classes:

| Test Class         | Coverage Area                                   |
|--------------------|------------------------------------------------|
| `ByteUtilTest`     | Byte/int conversion, bit operations            |
| `ConstantsTest`    | Protocol constants and message formats         |
| `FlagsTest`        | Filter flag management                         |
| `OutputUtilTest`   | Hex message formatting                         |
| `ReceiverTest`     | Message processing and opcode handling         |
| `RecordTest`       | Macro record serialization/deserialization     |

---

## Project Structure

```
src/
├── main/java/
│   ├── Main.java                 # Application entry point
│   ├── Utilities/
│   │   ├── ByteUtil.java         # Byte/bit manipulation utilities
│   │   ├── Constants.java        # Protocol constants and UI strings
│   │   └── Flags.java            # Runtime configuration flags
│   ├── connection/
│   │   ├── SocketConnector.java  # TCP socket management
│   │   ├── Sender.java           # Threaded message sender with queue
│   │   ├── Receiver.java         # Threaded message receiver & processor
│   │   ├── ServerReload.java     # Server heartbeat monitor
│   │   ├── OutputUtil.java       # Hex message formatting
│   │   └── QuestionUtil.java     # Reconnection dialog
│   ├── console/
│   │   ├── Console.java          # GUI console (redirects stdout/stderr)
│   │   ├── Menu.java             # Menu bar and toolbar
│   │   ├── PopUp_IP_Port.java    # IP/Port connection dialog
│   │   ├── Filter.java           # Message filter dialog
│   │   └── About.java            # About dialog
│   ├── makro/
│   │   ├── Makro.java            # Macro recording/playback engine
│   │   └── Record.java           # Serializable macro record
│   └── io/
│       └── IO.java               # Configuration file I/O
└── test/java/
    ├── Utilities/
    │   ├── ByteUtilTest.java
    │   ├── ConstantsTest.java
    │   └── FlagsTest.java
    ├── connection/
    │   ├── OutputUtilTest.java
    │   └── ReceiverTest.java
    └── makro/
        └── RecordTest.java
```

---

## RMXnet Protocol

Messages follow the format: `HEAD | COUNT | OPCODE | DATA`

| Field   | Size    | Description                      |
|---------|---------|----------------------------------|
| HEAD    | 1 byte  | Always `0x7C`                    |
| COUNT   | 1 byte  | Total message length (in bytes)  |
| OPCODE  | 1 byte  | Operation code                   |
| DATA    | N bytes | Opcode-specific payload          |

### Opcodes (Receiving)

| Opcode | Hex    | Description                      |
|--------|--------|----------------------------------|
| 0      | `0x00` | Positive acknowledgement         |
| 1      | `0x01` | Negative acknowledgement         |
| 3      | `0x03` | Initialization response          |
| 4      | `0x04` | State info                       |
| 6      | `0x06` | RMX address value (RMX-1 Bus)   |
| 8      | `0x08` | Lok info (address, type, name)   |
| 32     | `0x20` | RMX channel info (RMX-0 Bus)    |
| 36     | `0x24` | Lok info (speed and direction)   |
| 40     | `0x28` | Lok info (functions f0–f16)      |
| 192    | `0xC0` | Read lok decoder                 |

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -m 'Add my feature'`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Open a Pull Request

---

## Authors

- **Jan Dammrath**
- **Matthias Mack**
- **Angelo Gennaro**
