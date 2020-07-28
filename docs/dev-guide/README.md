# Developers guide

## Table of Contents

- [Starting development](#starting-development)
- [Git commit message style guide](git-commit-message-style-guide.md)
- [Build Tool](#build-tool)

## Starting Development
Before starting the development, please, run the
[`git/init.sh`](../../git/init.sh) script. This script initializes
your local repository configurations with properties used on the project:
```bash
sh git/init.sh
``` 

## Build Tool
`sbt` is used as a build tool on the project to jump into an `sbt` console
you could simply run `sbt` in the root of the project. Run `tasks` to see
list of the available tasks.
