#!/bin/bash
set -e

# allow overriding git source mirror, default github.com
GITHUB_BASE=${GITHUB_MIRROR:-https://github.com}

source "buildScript/init/env.sh"
ENV_NB4A=1
source "buildScript/lib/core/get_source_env.sh"
pushd ..

####

if [ ! -d "sing-box" ]; then
  git clone --no-checkout "$GITHUB_BASE/MatsuriDayo/sing-box.git"
fi
pushd sing-box
git checkout "$COMMIT_SING_BOX"
popd

####

if [ ! -d "libneko" ]; then
  git clone --no-checkout "$GITHUB_BASE/MatsuriDayo/libneko.git"
fi
pushd libneko
git checkout "$COMMIT_LIBNEKO"
popd

####

popd
