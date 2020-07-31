#!/usr/bin/env bash

script_dir=$(dirname "${0}")

function main {
  set_git_config
  install_hooks
}

# Adds the project-specific configurations to the repository-level
# configuration file.
function set_git_config {
  git config --local include.path "../git/.gitconfig"
}

function install_hooks {
  "${script_dir}"/hooks/install.sh
}

main "${@}"
