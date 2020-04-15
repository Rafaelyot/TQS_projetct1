FROM node

# set working directory
WORKDIR /docker_app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /docker_app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install --silent

# add app
COPY . ./
RUN npm run build

# start app
CMD ["npm", "start"]