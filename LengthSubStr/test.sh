#!/bin/bash
yum install -y gcc-c++  pcre pcre-devel zlib zlib-devel openssl openssl-devel patch
wget -c https://nginx.org/download/nginx-1.18.0.tar.gz

git clone  https://github.com/chobits/ngx_http_proxy_connect_module.git

tar -zxvf nginx-1.18.0.tar.gz

path=$(pwd)

cd nginx-1.18.0

patch -p1 < $path/ngx_http_proxy_connect_module/patch/proxy_connect_rewrite_1018.patch

./configure --with-http_ssl_module --add-module==$path/ngx_http_proxy_connect_module
make && make install
