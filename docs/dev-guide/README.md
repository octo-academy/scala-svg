# Developers guide

## Table of Contents

- [Starting development](#starting-development)
- [Git commit message style guide](git-commit-message-style-guide.md)
- [IDE](#ide)
- [Build Tool](#build-tool)

## Starting Development
Before starting the development, please, run the
[`git/init.sh`](../../git/init.sh) script. This script initializes
your local repository configurations with properties used on the project:
```bash
sh git/init.sh
``` 

## IDE
The only integrated development environment that is compatible with dotty at
the moment is `vs-code`. You can install it from the official page
(code.visualstudio.com). **Note**: set up ability to run `vs-code` from a
terminal because it is required for the first run of `sbt`.

Once `vs-code` is installed you can open the project in IDE running the
following command from the root dirrectory of the project:
```bash
sbt launchIDE
```

You can find more details onthe official page - dotty.epfl.ch/docs/usage/ide-support.html

## Build Tool
`sbt` is used as a build tool on the project to jump into an `sbt` console
you could simply run `sbt` in the root of the project. Run `tasks` to see
list of the available tasks.
