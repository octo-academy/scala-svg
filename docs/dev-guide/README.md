<!-- omit in toc -->
# Developers guide

- [Starting Development](#starting-development)
- [IDE](#ide)
- [Build Tool](#build-tool)
- [Code style](#code-style)

<!-- Used references -->
[vs-code]: https://code.visualstudio.com
[dotty-ide-support]: https://dotty.epfl.ch/docs/usage/ide-support.html
[scalafmt]: https://scalameta.org/scalafmt

## Starting Development
Before starting the development, please, run the
[`git/init.sh`](../../git/init.sh) script. This script initializes
your local repository configurations with properties used on the project:
```bash
sh git/init.sh
``` 
Please, **see also**
[Git commit message style guide](git-commit-message-style-guide.md).

## IDE
The only integrated development environment that is compatible with dotty at
the moment is `vs-code`. You can install it from the official page
[Visual Studio Code][vs-code].

**Note**: set up the ability to run `vs-code` from a terminal because it is
required for the first run of `sbt`.

Once `vs-code` is installed you can open the project in IDE running the
following command from the root directory of the project:
```bash
sbt launchIDE
```

You can find more details on the official page
[Dotty IDE support][dotty-ide-support].

## Build Tool
`sbt` is used as a build tool on the project to jump into an `sbt` console you
could simply run `sbt` in the root of the project. Run `tasks` to see a list of
the available tasks.

## Code style
[`scalafmt`][scalafmt] is used as a linter on the project, please use the tasks
it provides to check and format your code.
