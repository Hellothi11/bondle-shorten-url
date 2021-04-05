FROM node:14.16.0-alpine3.12 as build
ARG BUILD_ENV=local
ENV BUILD_ENV=$BUILD_ENV
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY yarn.lock ./
COPY . ./
RUN yarn install
RUN yarn run build:$BUILD_ENV

FROM nginx:stable-alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]