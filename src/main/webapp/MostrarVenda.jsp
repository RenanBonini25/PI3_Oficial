<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Carrinho</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo!</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="clientes.jsp"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="games.jsp"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="consoles.jsp"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="acessorios.jsp"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="actionFigure.jsp"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="usuarios.jsp"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="filiais.jsp"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="venda.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="relatorio.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <form action="" method="" id="formularioCadastro">
                <table cellpadding="10">
                    <h2>Venda efetuada com sucesso!</h2>
                    <tr>
                        <h3>Cliente: ${sessionScope.cliente.nome}</h3>
                    </tr>
                    <tr>
                        <th>Nome</th>
                        <th>Quantidade</th>
                        <th>Subtotal</th>

                    </tr>
                    <c:forEach items="${sessionScope.carrinho}" var="item">
                        <tr>
                            <td>${item.produto.nome}</td>
                            <td>${item.quantidade}</td>
                            <td>${item.subtotal}</td>

                        </tr>
                    </c:forEach>
                        <tr>
                            <td><b>Total Venda:</b> ${sessionScope.totalVenda}</td>
                        </tr>
                </table>

                <button onclick="location.href = 'Vendas_ProcurarCliente.jsp'" type="button">
                    Realizar nova compra</button>
                        <button onclick="location.href = 'home.jsp'" type="button">
                    Voltar para home</button>
            </form>
        </div>
    </body>
</html>