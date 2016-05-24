#!/usr/bin/env bash

# A script to install Git hook scripts.

echo "Installing Git hooks."
pushd $(dirname $0) > /dev/null

GIT_HOOK_DIR=$(git rev-parse --git-dir)/hooks
GIT_HOOKS="pre-push"

for name in ${GIT_HOOKS}; do
   echo "* Installing $name..."
   hook="$GIT_HOOK_DIR/$name"
   # Backup any non symlink git hook
   if [ -f $hook ] && [ ! -h $hook ]; then
       echo "$hook file already exists. Taking a backup ${hook}.bk"
       mv $hook $hook.bk
   fi
   # Creating a symlink to DnB scripts
   ln -sf $(pwd)/$name $hook
   # Make it executable
   chmod +x $hook
done

popd > /dev/null
echo "Completed!"