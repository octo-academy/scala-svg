<!-- omit in toc -->
# Developers guide
- [Git](#git)
  - [The First Thing to Do After You Clone the Project](#the-first-thing-to-do-after-you-clone-the-project)
  - [Git commit message style guide](#git-commit-message-style-guide)
- [IDE](#ide)
- [Build Tool](#build-tool)
- [Code style](#code-style)

<!-- References -->
[vs-code]: https://code.visualstudio.com
[dotty-ide-support]: https://dotty.epfl.ch/docs/usage/ide-support.html
[scalafmt]: https://scalameta.org/scalafmt
[git-init-script]: ../../git/init.sh
[git-message-template]: ../../git/.gitmessage
[git-commit-message-style-guide]: git-commit-message-style-guide.md

## Git
### The First Thing to Do After You Clone the Project
Before starting the development, please, run the [init][git-init-script]
script. This script initializes your local repository configurations with
properties used on the project:
```bash
bash git/init.sh
```
It will set up all git hooks and configure git preferences such as newline
symbol conversions and git other attributes. It will also set up a [commit
message template][git-message-template] that if you commit from the console
without `-m | --message` option will provide you with guidelines and quick
reference on the message style guide.

### Git commit message style guide
Please, see a separate file for this part of the documentation - 
[Git commit message style guide][git-commit-message-style-guide].

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
